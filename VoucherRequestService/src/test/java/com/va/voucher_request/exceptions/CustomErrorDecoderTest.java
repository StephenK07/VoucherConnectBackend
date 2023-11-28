//package com.va.voucher_request.exceptions;
//
//import static org.mockito.Mockito.when;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.nio.charset.Charset;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import com.va.voucher_request.errordecoder.CustomErrorDecoder;
//
//import feign.Response;
//import feign.codec.ErrorDecoder;
//
//public class CustomErrorDecoderTest {
//   
//	
//	  @Mock
//	  private Response response;
//	
//	 @InjectMocks
//	 private CustomErrorDecoder customErrorDecoder;
//
//	  
//
//	    // Test Case 1: Decode 404 Error Response
//	    @Test
//	    void testDecode404ErrorResponse() {
//	        // Mock response status
//	        when(response.status()).thenReturn(404);
//	        // Mock response body
//	        when(response.body()).thenReturn(mockResponseBody("Not Found"));
//
//	        // Perform the decode and assert the exception type and message
//	        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
//	                () -> customErrorDecoder.decode("methodKey", response));
//
//	        Assertions.assertEquals("Not Found", exception.getMessage());
//	    }
//
//	    // Test Case 2: Decode 406 Error Response
//	    @Test
//	    void testDecode406ErrorResponse() {
//	        // Mock response status
//	        when(response.status()).thenReturn(406);
//	        // Mock response body
//	        when(response.body()).thenReturn(mockResponseBody("Resource Already Exists"));
//
//	        // Perform the decode and assert the exception type and message
//	        ResourceAlreadyExistException exception = Assertions.assertThrows(ResourceAlreadyExistException.class,
//	                () -> customErrorDecoder.decode("methodKey", response));
//
//	        Assertions.assertEquals("Resource Already Exists", exception.getMessage());
//	    }
//
//	
//
//	    // Helper method to mock response body
//	    private Response.Body mockResponseBody(String responseBody) {
//	        return new Response.Body() {
//	            @Override
//	            public Integer length() {
//	                return responseBody.length();
//	            }
//
//	            @Override
//	            public boolean isRepeatable() {
//	                return false;
//	            }
//
//	            @Override
//	            public InputStream asInputStream() throws IOException {
//	                return new ByteArrayInputStream(responseBody.getBytes());
//	            }
//
//	            @Override
//	            public Reader asReader() throws IOException {
//	                return new InputStreamReader(asInputStream());
//	            }
//
//	            @Override
//	            public void close() {
//
//	            }
//
//				@Override
//				public Reader asReader(Charset charset) throws IOException {
//					// TODO Auto-generated method stub
//					return null;
//				}
//	        };
//	    
//}
//}
