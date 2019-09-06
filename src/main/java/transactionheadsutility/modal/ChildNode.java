package transactionheadsutility.modal;

/**
 * @author Vishal Kumar &lt;piyush.sharma@nucleussoftware.com&gt; on 9/6/2019.
 */
public class ChildNode {
    private String text;
    private boolean children = true;
    private String id;
    private String icon = "folder";

    public ChildNode(String id, String text) {
        setId(id);
        setText(text);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
