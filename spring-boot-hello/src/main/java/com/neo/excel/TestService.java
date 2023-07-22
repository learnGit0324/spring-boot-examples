package com.neo.excel;

import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface TestService {
    void doDownload(HttpServletResponse response);
}
