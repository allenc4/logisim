/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.start;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;

import com.cburch.logisim.Main;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.util.GraphicsUtil;

public class About {
	static final int IMAGE_BORDER = 10;
	static final int IMAGE_WIDTH = 380;
	static final int IMAGE_HEIGHT = 284;
	protected static JSVGCanvas svgCanvas =new JSVGCanvas();
	
	private static class MyPanel extends JPanel{

		public MyPanel() {
			setLayout(null);
			int prefWidth = IMAGE_WIDTH + 2 * IMAGE_BORDER;
			int prefHeight = IMAGE_HEIGHT + 2 * IMAGE_BORDER;
			setPreferredSize(new Dimension(prefWidth, prefHeight));
			setBackground(Color.WHITE);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
	
	public static JComponent createComponents() {
		final JPanel panel = new JPanel(new BorderLayout());
		panel.add("Center", svgCanvas);
		svgCanvas.setURI(About.class.getResource("/logisim/drawing.svg").toString());
		svgCanvas.addSVGDocumentLoaderListener(new SVGDocumentLoaderAdapter() {});
		svgCanvas.addGVTTreeBuilderListener(new GVTTreeBuilderAdapter() {});
		svgCanvas.addGVTTreeRendererListener(new GVTTreeRendererAdapter() {});
		return panel;
	}

	public static void showAboutDialog(JFrame owner) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createComponents());
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JOptionPane.showMessageDialog(owner, panel,
				"Logisim " + Main.VERSION_NAME, JOptionPane.PLAIN_MESSAGE);
	}
}