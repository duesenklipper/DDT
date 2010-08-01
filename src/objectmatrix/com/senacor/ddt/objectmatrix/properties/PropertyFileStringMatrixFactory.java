/*
 * Copyright (c) 2007 Senacor Technologies AG.
 *  
 * All rights reserved. Redistribution and use in source and binary forms,
 * with or without modification, are permitted provided that the following
 * conditions are met: 
 *
 * Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer. 
 *
 * Redistributions in binary form must reproduce the above copyright 
 * notice, this list of conditions and the following disclaimer in the 
 * documentation and/or other materials provided with the distribution. 
 *
 * Neither the name of Senacor Technologies AG nor the names of its 
 * contributors may be used to endorse or promote products derived from 
 * this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS 
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED 
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */

package com.senacor.ddt.objectmatrix.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.senacor.ddt.objectmatrix.AbstractDefaultStringMatrixBasedObjectMatrixFactory;
import com.senacor.ddt.objectmatrix.DefaultStringMatrix.StringMatrixReader;

/**
 * FIXME: Testen. Dokumentieren.
 * 
 * @author Ralph Winzinger
 * @author Carl-Eric Menzel
 */
public class PropertyFileStringMatrixFactory extends AbstractDefaultStringMatrixBasedObjectMatrixFactory {
  private final String[] fileNames;
  
  private static final Log log = LogFactory.getLog(PropertyFileStringMatrixFactory.class);
  
  private final String identifier;
  
  public PropertyFileStringMatrixFactory(final String[] fileNames, final String identifier) {
    this.fileNames = fileNames;
    this.identifier = identifier;
    this.log.info("PropertyFileStringMatrixFactory initialized with following files:");
    for (int i = 0; i < fileNames.length; i++) {
      final String fileName = fileNames[i];
      this.log.info(" > " + fileName);
    }
  }
  
  protected StringMatrixReader[] createReaders() {
    return new StringMatrixReader[] { new PropertyFileStringMatrixReader(this.fileNames, this.identifier) };
  }
}