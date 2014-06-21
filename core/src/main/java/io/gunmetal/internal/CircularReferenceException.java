/*
 * Copyright (c) 2013.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gunmetal.internal;

import io.gunmetal.spi.ComponentMetadata;
import io.gunmetal.spi.ProvisionStrategy;

import java.util.Stack;

/**
 * @author rees.byars
 */
class CircularReferenceException extends RuntimeException {

    private static final long serialVersionUID = -7837281223529967792L;
    private final ComponentMetadata<?> metadata;
    private ProvisionStrategy<?> reverseStrategy;
    private final Stack<ComponentMetadata<?>> componentMetadataStack = new Stack<>();

    CircularReferenceException(ComponentMetadata<?> metadata) {
        this.metadata = metadata;
    }

    public ComponentMetadata<?> metadata() {
        return metadata;
    }

    public void setReverseStrategy(ProvisionStrategy<?> reverseStrategy) {
        this.reverseStrategy = reverseStrategy;
    }

    public ProvisionStrategy<?> getReverseStrategy() {
        return reverseStrategy;
    }

    public void push(ComponentMetadata<?> componentMetadata) {
        componentMetadataStack.push(componentMetadata);
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder("Circular dependency detected -> \n");
        builder.append("    ").append(metadata);
        while (!componentMetadataStack.empty()) {
            builder.append("\n     was requested by ");
            builder.append(componentMetadataStack.pop());
        }
        return builder.toString();
    }

}
