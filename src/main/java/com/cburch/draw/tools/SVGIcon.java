package com.cburch.draw.tools;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

public class SVGIcon implements Icon {

	private String path;
	private GraphicsNode svgIcon = null;
	private final static int HEIGHT = 16;
	private final static int WIDTH = 16;

    public SVGIcon(String path) {
        try {
            // Quick and dirty hack. We should probably use a path resolver.
            this.path = (path.startsWith("/logisim/icons/")) ? path : "/logisim/icons/" + path;
            URL url = SVGIcon.class.getResource(this.path);
            String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory df = new SAXSVGDocumentFactory(xmlParser);
            SVGDocument doc = df.createSVGDocument(url.toString());
            UserAgent userAgent = new UserAgentAdapter();
            DocumentLoader loader = new DocumentLoader(userAgent);
            BridgeContext ctx = new BridgeContext(userAgent, loader);
            ctx.setDynamicState(BridgeContext.DYNAMIC);
            GVTBuilder builder = new GVTBuilder();
            this.svgIcon = builder.build(ctx, doc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int getIconHeight() {
        return HEIGHT;
    }

    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    private void paintSvgIcon(Graphics2D g, int x, int y, double scaleX, double scaleY) {
            AffineTransform transform = new AffineTransform(scaleX, 0.0, 0.0, scaleY, x, y);
            svgIcon.setTransform(transform);
            svgIcon.paint(g);
        }

    @Override
    public void paintIcon(Component arg0, Graphics g, int x, int y) {
        paintSvgIcon((Graphics2D)g,x,y,1,1);
    }

}
