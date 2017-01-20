package by.tc.FileAnalyzer.bean;

import java.io.Serializable;

public class NodeInfo implements Serializable{
    private String content;
    private NodeType type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeInfo nodeInfo = (NodeInfo) o;

        if (!content.equals(nodeInfo.content)) return false;
        return type == nodeInfo.type;

    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
