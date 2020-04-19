package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esiea.tp4A.spring.SpringController;

@ExtendWith(MockitoExtension.class)
public class TestServer 
{
    @InjectMocks
    SpringController controller;
     
    @Test
    public void testCreatePlayerTrue() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        ResponseEntity<String> responseEntity = controller.create("Player");
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }
    
    @Test
    public void testCreatePlayerFalse() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        controller.create("Player");
        ResponseEntity<String> responseEntity1 = controller.create("Player");
         
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(409);
    }
    
    @Test
    public void testLoadPlayerFalse() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        ResponseEntity<String> responseEntity = controller.loadPlayer("Player");
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
    
    @Test
    public void testLoadPlayerTrue() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        controller.create("Player");
        ResponseEntity<String> responseEntity1 = controller.loadPlayer("Player");
         
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testActionTrue() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        controller.create("Player");
        ResponseEntity<String> responseEntity1 = controller.action("Player", "f");
         
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testActionFalsePlayerNotExisting() throws Exception 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        ResponseEntity<String> responseEntity1 = controller.action("Player", "f");
         
        assertThat(responseEntity1.getStatusCodeValue()).isEqualTo(404);
    }
    
    @Test
    public void testHtml() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        String responseEntity1 = controller.first();
        assertEquals(responseEntity1,"pages/web");
         
    }
    
}