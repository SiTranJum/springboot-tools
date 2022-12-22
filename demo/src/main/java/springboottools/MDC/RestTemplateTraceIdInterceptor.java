package springboottools.MDC;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * RestTemplate远程调用拦截MDC
 * 给header增加MDC标识，远程调用接收到标识则会注入MDC
 */
public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        String traceId = MDC.get(MDCConstants.TRACE_ID);
        if (traceId != null) {
            httpRequest.getHeaders().add(MDCConstants.TRACE_ID, traceId);
        }
 
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}