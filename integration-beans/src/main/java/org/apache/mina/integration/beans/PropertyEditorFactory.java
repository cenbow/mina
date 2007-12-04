/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.integration.beans;

import java.beans.PropertyEditor;

/**
 * A factory that creates a new {@link PropertyEditor} which is appropriate for
 * the specified object or class. 
 * 
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev$, $Date$
 */
public final class PropertyEditorFactory {
    public static PropertyEditor getInstance(Object object) {
        if (object == null) {
            throw new NullPointerException("object");
        }
        
        return getInstance(object.getClass());
    }
    
    public static PropertyEditor getInstance(Class<?> type) {
        if (type == null) {
            throw new NullPointerException("type");
        }
        
        try {
            return (PropertyEditor) PropertyEditorFactory.class.getClassLoader().loadClass(
                    PropertyEditorFactory.class.getPackage().getName() + '.' +
                    type.getSimpleName() + "Editor").newInstance();
        } catch (Exception e) {
            return null;
        }
    }
    
    private PropertyEditorFactory() {
    }
}