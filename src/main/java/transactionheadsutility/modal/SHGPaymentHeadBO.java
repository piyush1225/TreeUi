package transactionheadsutility.modal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SHGPaymentHeadBO {

    private List<Map<String, List<SHGHeaderParameterBO>>> headers;
    private List<Map<String, List<SHGHeaderMapParameterBO>>> headerMap;
    private List<Map<String, List<SHGHeaderMapParameterBO>>> headerMapParent;
    private HashMap<String, String> entity = new HashMap<>();

    public List<Map<String, List<SHGHeaderParameterBO>>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Map<String, List<SHGHeaderParameterBO>>> headers) {
        this.headers = headers;
    }

    public List<Map<String, List<SHGHeaderMapParameterBO>>> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(List<Map<String, List<SHGHeaderMapParameterBO>>> headerMap) {
        this.headerMap = headerMap;
    }

    public List<Map<String, List<SHGHeaderMapParameterBO>>> getHeaderMapParent() {
        return headerMapParent;
    }

    public void setHeaderMapParent(List<Map<String, List<SHGHeaderMapParameterBO>>> headerMapParent) {
        this.headerMapParent = headerMapParent;
    }

    public HashMap<String, String> getEntity() {
        return entity;
    }

    public void setEntity(HashMap<String, String> entity) {
        this.entity = entity;
    }
}
