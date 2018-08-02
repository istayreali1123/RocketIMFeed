package com.thanos.sns.mail;

import com.thanos.common.utils.RandomUtils;

/**
 * Created by wangjialong on 7/24/18.
 */
public class SkipList {

    private static final int ZSKIPLIST_MAXLEVEL = 64;

    ZskiplistNode head;

    ZskiplistNode tail;

    // 节点数量
    long length;

    // 节点最大层数
    int level;

    public ZskiplistNode insert(long score, Object obj) {
        ZskiplistNode[] update = new ZskiplistNode[ZSKIPLIST_MAXLEVEL];

        // 第i层 待插入节点前驱节点的排名
        int rank[] = new int[ZSKIPLIST_MAXLEVEL];

        ZskiplistNode x = head;
        for (int i = this.level-1; i>=0; i--) {
            rank[i] = i == (this.level-1)? 0: rank[i + 1];
            while (x.level[i].forward != null &&
                    x.level[i].forward.score <= score) {
                rank[i] += x.level[i].span;
                x = x.level[i].forward;
            }
            update[i] = x;
        }
        int newLevel = randomLevel();
        ZskiplistNode newNode = zslCreateNode(newLevel, score, obj);
        if (newLevel > level) {
            for (int i = level; i < newLevel; i++) {
                update[i] = head;
            }
        }
        for (int i=0; i<level; i++) {
            newNode.level[i].forward = update[i].level[i].forward;
            update[i].level[i].forward = newNode;

            // update the value of span
            newNode.level[i].span = update[i].level[i].span - (rank[0] - rank[1]);
            update[i].level[i].span = rank[0] - rank[i] + 1;
        }
        return newNode;
    }

    public int randomLevel() {
       return RandomUtils.getRandomInt(ZSKIPLIST_MAXLEVEL + 1);
    }

    public SkipList() {
        this.level = 1;
        this.length = 0;
        this.head = this.zslCreateNode(ZSKIPLIST_MAXLEVEL, 0, null);

        for (int j=0; j < ZSKIPLIST_MAXLEVEL; j++) {
            head.level[j].forward = null;
            head.level[j].span = 0;
        }
        tail = null;
        head.backword = null;
    }

    public ZskiplistNode zslCreateNode(int level, long score, Object value) {
        ZskiplistNode.zskiplistLevel[] levelList =
                new ZskiplistNode.zskiplistLevel[level];
        for (int i = 0; i < level; ++i) {
            levelList[i] = new ZskiplistNode.zskiplistLevel(null, 0);
        }
        ZskiplistNode node = new ZskiplistNode(value, score, null, levelList);
        return node;
    }


    protected static class ZskiplistNode {

        Object value;

        public ZskiplistNode(Object value, long score, ZskiplistNode backword, zskiplistLevel[] level) {
            this.value = value;
            this.score = score;
            this.backword = backword;
            this.level = level;
        }

        long score;

        ZskiplistNode backword;

        protected static class zskiplistLevel {

            ZskiplistNode forward;

            public zskiplistLevel(ZskiplistNode forward, int span) {
                this.forward = forward;
                this.span = span;
            }

            int span;
        }

        zskiplistLevel[] level;
    }
}
