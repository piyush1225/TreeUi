package transactionheadsutility.controller;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import transactionheadsutility.modal.Node;
import transactionheadsutility.modal.SHGPaymentHeads;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class UtilityController {

    private static final Logger logger = LoggerFactory.getLogger(UtilityController.class);

    @Autowired
    private HttpSession session;

    @PostMapping(value = "/controller")
    public ResponseEntity updateUserStatus(HttpServletRequest request, @RequestBody String data) {
        logger.debug("<IN> updateUserStatus");
        SHGPaymentHeads shgPaymentHeads = new Gson().fromJson(data, SHGPaymentHeads.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView viewHome() {
        return new ModelAndView("welcome");
    }

    private Node findNode(Node rootNode, String id) {
        Node nn = null;
        if (id.equals(rootNode.getId())) {
            return rootNode;
        } else if (!rootNode.getChildren().isEmpty()){
                for (Node node : rootNode.getChildren()) {
                    if (null != (nn = findNode(node, id))) {
                        break;
                    }
                }
            }
            return nn;
    }


    @ResponseBody
    @GetMapping(value = "/data", produces = "application/json")
    public Object viewHome(@RequestParam(value = "operation", required = false) String operation,
                           @RequestParam(value = "id", required = false)String id,
                           @RequestParam(value = "text", required = false)String text) {
        Node node = null;
        if (null != session) {
            node = (Node) session.getAttribute("KEY_");
            if (id != null && operation != null) {
                switch (operation) {
                    case "get_content":
                        node = findNode(node, id);
                        break;
                    case "delete_node":
                        Node mainNode = node;
                        node = findNode(node, id);
                        String parent = node.getParentId();
                        findNode(mainNode, parent).getChildren().remove(node);
                        break;
                    case "get_node":

                        break;
                    case "create_node":
                        node = findNode(node, id);
                        String uniqId = String.valueOf(System.currentTimeMillis());
                        Node childNode = new Node(uniqId, text);
                        childNode.setParentId(id);
                        if (CollectionUtils.isNotEmpty(node.getChildren())) {
                            node.getChildren().add(childNode);
                        } else {
                            List<Node> nodes = new ArrayList<>();
                            nodes.add(childNode);
                            node.setChildren(nodes);
                        }
                        break;
                    case "rename_node":
                        node = findNode(node, id);
                        node.setText(text);
                        break;
                }
            }
            if (null == node) {
                node = new Node("1", "root");
                /*Node ch = new Node("2", "Loan 2");
                ch.setParentId(node.getId());
                node.setChildren(Collections.singletonList(ch));

                Node ch1 = new Node("3", "Loan 3");
                ch1.setParentId(ch.getId());
                ch.setChildren(Collections.singletonList(ch1));*/
                session.setAttribute("KEY_", node);
            }
        }
        return new Gson().toJson(Collections.singleton(node));//.replace("\\\\", "\\");
    }
}
