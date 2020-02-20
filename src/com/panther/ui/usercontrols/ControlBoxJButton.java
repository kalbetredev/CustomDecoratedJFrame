package com.panther.ui.usercontrols;

import com.panther.ui.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlBoxJButton extends JButton implements MouseListener {
   public final ButtonType controlBoxButtonType;
   private final Theme theme;

   public ControlBoxJButton(ButtonType controlBoxButtonType, Theme theme) {
      setOpaque(false);
      addMouseListener(this);
      this.controlBoxButtonType = controlBoxButtonType;
      this.theme = theme;
   }

   @Override
   public Insets getInsets() {
      return new Insets(0, 0, 0, 0);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int width = getWidth();
      int height = getHeight();

      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setColor(getBackground());
      g2d.fillRect(0, 0, width, height);

      g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g2d.setColor(theme.getLightForegroundColor());

      Point markStPt = new Point(width / 2 - 5, (height / 2 - 5));
      Point markEnPt = new Point(width / 2 + 5, (height / 2 + 5));

      switch (controlBoxButtonType){
         case MINIMIZE:
            markStPt = new Point((width / 2 - 5),(height / 2));
            markEnPt = new Point((width / 2 + 5),(height / 2));
            g2d.drawLine(markStPt.x, markStPt.y, markEnPt.x, markEnPt.y);
            break;
         case MAXIMIZE:
            g2d.drawRect(markStPt.x, markStPt.y, 10, 10);
            break;
         case CLOSE:
            g2d.drawLine(markStPt.x, markStPt.y, markEnPt.x, markEnPt.y);
            markStPt = new Point((width / 2 + 5),(height / 2 - 5));
            markEnPt = new Point((width / 2 - 5),(height / 2 + 5));
            g2d.drawLine(markStPt.x, markStPt.y, markEnPt.x, markEnPt.y);
            break;
      }
      g2d.dispose();
   }

   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mousePressed(MouseEvent e) {
      if(controlBoxButtonType == ButtonType.CLOSE)
         setBackground(theme.getCloseButtonPressedColor());
      else
         setBackground(theme.getDefaultButtonHoverColor());
   }

   @Override
   public void mouseReleased(MouseEvent e) {

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      if(controlBoxButtonType == ButtonType.CLOSE)
         setBackground(theme.getCloseButtonHoverColor());
      else
         setBackground(theme.getDefaultButtonHoverColor());
   }

   @Override
   public void mouseExited(MouseEvent e) {
      setBackground(theme.getDefaultBackgroundColor());
   }
}