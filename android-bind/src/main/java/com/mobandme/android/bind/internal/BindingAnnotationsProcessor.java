/*
 * Copyright Txus Ballesteros 2015 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */

package com.mobandme.android.bind.internal;

import android.widget.Toast;

import java.util.Set;
import java.io.IOException;

import javax.naming.Context;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import javax.tools.JavaFileObject;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.AbstractProcessor;
import com.mobandme.android.bind.annotations.BindTo;
import javax.annotation.processing.SupportedSourceVersion;
import javax.annotation.processing.SupportedAnnotationTypes;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes(
        "com.mobandme.android.bind.annotations.BindTo"
)
public class BindingAnnotationsProcessor extends AbstractProcessor {

    BufferedWriter buffer;
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing binding annotations.");

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(BindTo.class);
        if (annotatedElements != null && annotatedElements.size() > 0) {

            createJavaSourceFile();
            
            for(Element annotatedElement : annotatedElements) {
                BindTo bindTo = annotatedElement.getAnnotation(BindTo.class);

                if (bindTo != null)
                    parseBinToAnnotation(bindTo, annotatedElement);
            }

            closeJavaSourceFile();
        }
        
        return true;
    }
    
    private void createJavaSourceFile() {

        try {
            
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "--> Generating Java source file.");
            
            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile("ModelViewMapper");
            buffer = new BufferedWriter(javaFileObject.openWriter());
            buffer.append("package com.mobandme.android.bind.compiler;");
            buffer.newLine();
            buffer.newLine();
            buffer.append("import android.content.Context;");
            buffer.newLine();
            buffer.append("import android.widget.Toast;");
            buffer.newLine();
            buffer.newLine();
            buffer.append("public final class ModelViewMapper extends AbstractModelViewMapper {");
            buffer.newLine();
            buffer.newLine();
            buffer.append("\tpublic ModelViewMapper() {");
            buffer.newLine();
            buffer.append("\t\tsuper();");
            buffer.newLine();
            buffer.append("\t}");

            
            //TODO: Erase
            buffer.newLine();
            buffer.newLine();
            buffer.append("\t@Override");
            buffer.newLine();
            buffer.append("\tpublic void test(Context context) {");
            buffer.newLine();
            buffer.append("\t\tToast.makeText(context, \"Works!!!\", Toast.LENGTH_SHORT).show();");
            buffer.newLine();
            buffer.append("\t}");
            
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
    
    private void closeJavaSourceFile() {
        try {

            buffer.newLine();
            buffer.append("}");
            buffer.close();

            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "--> Closing Java source file.");
            
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
    
    private void parseBinToAnnotation(BindTo annotation, Element element) {
        String traceMessagePattern = "--> Processing %s field annotations.";
        String traceMessage = String.format(traceMessagePattern, element.getSimpleName());
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, traceMessage);

    }
}
