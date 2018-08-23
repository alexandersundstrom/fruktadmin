package com.evry.client;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ClickableArrowHeaderCell extends ClickableTextCell {
    private String style;
    private boolean isAscending;

    public ClickableArrowHeaderCell(boolean isAscending) {
        super();
        style = "arrow-header";
        this.isAscending = isAscending;
    }

    @Override
    protected void render(Context context, SafeHtml value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.appendHtmlConstant("<div class=\"" + style + "\">");
            if (isAscending) {
                sb.appendHtmlConstant("<img class=\"arrow-image\" src=\"arrow_down.png\"/>");
            } else {
                sb.appendHtmlConstant("<img class=\"arrow-image\" src=\"arrow_up.png\"/>");
            }
            sb.append(value);
            sb.appendHtmlConstant("</div>");
        }
    }

    public void toggleSorting() {
        isAscending = !isAscending;
    }

    public boolean isAscending() {
        return isAscending;
    }
}