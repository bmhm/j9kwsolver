/**
 * J9KW Solver Library
 * Copyright (C) 2016, j9kwsolver contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */

package de.bmarwell.j9kwsolver.gui;

import de.bmarwell.j9kwsolver.response.RequestCaptchaResponse;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class J9kwShowImage {

  private static final Logger LOG = LoggerFactory.getLogger(J9kwShowImage.class);

  public String show(final RequestCaptchaResponse captcha) {
    Preconditions.checkNotNull(captcha, "captcha in show().");
    String solution = null;

    if (!captcha.image().isPresent()) {
      return "";
    }

    ImageIcon icon = new ImageIcon(captcha.image().get());
    JLabel iconLabel = new JLabel(icon);

    solution = JOptionPane.showInputDialog(iconLabel);

    LOG.debug("Solution: [{}].", solution);

    return solution;
  }
}
