package br.com.luizlmc.Dashboardvendas.event;

import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Long id;

    public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }
}