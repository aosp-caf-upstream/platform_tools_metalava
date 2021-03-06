/*
 * Copyright (C) 2010 Google Inc.
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

package com.android.tools.metalava.doclava1;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
// Copied from doclava1, but adapted to metalava's code model
//
public final class ApiParseException extends Exception {
    public String file;
    public int line;

    ApiParseException(@NotNull String message) {
        super(message);
    }

    ApiParseException(@NotNull String message, Exception cause) {
        super(message, cause);
        if (cause instanceof ApiParseException) {
            this.file = ((ApiParseException) cause).file;
            this.line = ((ApiParseException) cause).line;
        }
    }

    ApiParseException(@NotNull String message, @NotNull ApiFile.Tokenizer tokenizer) {
        this(message, tokenizer.getFileName(), tokenizer.getLine());
    }

    private ApiParseException(@NotNull String message, @Nullable String file, int line) {
        super(message);
        this.file = file;
        this.line = line;
    }

    ApiParseException(@NotNull String message, int line) {
        this(message, null, line);
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        if (file != null) {
            sb.append(file).append(':');
        }
        if (line > 0) {
            sb.append(Integer.toString(line)).append(':');
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(super.getMessage());
        return sb.toString();
    }
}
