package com.thanos.common.es;

import com.thanos.common.utils.ObjectTransform;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.Node;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_LANG;
import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_TYPE;

/**
 * Created by wangjialong on 6/13/18.
 */
public class EleasticSearchClient {

    private static TransportClient client = null;

    private static final String INDEX = "thanos";
    private static final String TYPE = "sns";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void start() {

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "thanos")
                    .build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createIndex() {
        try {
            ActionResponse resp = client.admin().indices().prepareCreate(INDEX).get();
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createMapping(Map<String, Object> mappings) throws RuntimeException {
        try {
            String json = MAPPER.writeValueAsString(mappings);
            System.out.println(json);

            PutMappingRequest request = Requests.putMappingRequest(INDEX).type(TYPE).source(json);
            client.admin().indices().putMapping(request).get();
        } catch (Exception e) {

        }

    }

    public static void createDocument(Object data, String id) {
        try {
            String source = MAPPER.writeValueAsString(data);
            Map<String, Object> obj = ObjectTransform.object2Map(data);
            ActionResponse response = client.prepareIndex(INDEX, TYPE, id).setSource(obj).get();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createOrUpdateDocumentByMap(String index, String type , String idOrCode,
                                                   Map<String, Object> params, Map<String, Object> upsert,
                                                   String id) {
        try {
            ActionResponse response = client.prepareUpdate(index, type, id)
                    .setScript(new Script(DEFAULT_SCRIPT_TYPE, DEFAULT_SCRIPT_LANG,
                            idOrCode, params)).setUpsert(upsert).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


// on shutdown
}
