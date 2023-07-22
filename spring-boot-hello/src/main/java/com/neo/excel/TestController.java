package com.neo.excel;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/download")
    public void windowsClientDownload(HttpServletResponse response, Long procedureId) {
        testService.doDownload(response);
    }
}
