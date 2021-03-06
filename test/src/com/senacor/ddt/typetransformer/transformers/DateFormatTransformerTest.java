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

package com.senacor.ddt.typetransformer.transformers;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import junit.framework.TestCase;

import com.senacor.ddt.test.util.GenericAssert;
import com.senacor.ddt.typetransformer.SpecificTransformer;
import com.senacor.ddt.typetransformer.TransformationFailedException;

public class DateFormatTransformerTest extends TestCase {
  private static final Calendar DATE_ONLY = new GregorianCalendar() {
    {
      clear();
      set(Calendar.DAY_OF_MONTH, 3);
      set(Calendar.MONTH, Calendar.OCTOBER);
      set(Calendar.YEAR, 2006);
    }
  };
  
  private static final Calendar DATE_AND_TIME = new GregorianCalendar() {
    {
      setTime(DATE_ONLY.getTime());
      set(Calendar.HOUR_OF_DAY, 8);
      set(Calendar.MINUTE, 5);
    }
  };
  
  public void testGermanLocaleDateOnly() throws Exception {
    final SpecificTransformer trans = new DateFormatTransformer(DateFormat.SHORT, Locale.GERMANY);
    assertEquals(DATE_ONLY, trans.transform("3.10.2006", Calendar.class));
    assertEquals(DATE_ONLY.getTime(), trans.transform("3.10.2006", Date.class));
    assertEquals(new java.sql.Date(DATE_ONLY.getTime().getTime()), trans.transform("3.10.2006", java.sql.Date.class));
    GenericAssert.assertNotEquals(DATE_ONLY, trans.transform("4.10.2006", Calendar.class));
  }
  
  public void testGermanLocaleDateAndTime() throws Exception {
    final DateFormatTransformer trans = new DateFormatTransformer(DateFormat.SHORT, DateFormat.SHORT, Locale.GERMANY);
    trans.setDebugMode(true);
    assertEquals(DATE_AND_TIME, trans.transform("3.10.2006 08:05", Calendar.class));
    GenericAssert.assertNotEquals(DATE_ONLY, trans.transform("4.10.2006 09:06", Calendar.class));
    try {
      trans.transform("4.10.2006", Calendar.class);
      fail("bad string, should have thrown exception");
    } catch (final TransformationFailedException e) {
      ; // expected
    }
  }
}
