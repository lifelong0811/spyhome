package com.jason.spy.financial.tool;


/**
 * @author wangleijie
 */
public class MarkdownTool {
    private StringBuilder str = new StringBuilder();

    public MarkdownTool header(String context) {
        str.append("### ").append(context).append("\n\n");
        return this;
    }

    public MarkdownTool quote(String context) {
        str.append("> ").append(context).append("\n\n");
        return this;
    }


    @Override
    public String toString() {
        return str.toString();
    }
}
