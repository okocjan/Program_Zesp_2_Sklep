package com.example.demo.app.tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class GenericResponse implements Serializable {

    private boolean success;
    private List<String> message;

    public GenericResponse() {
        message = new ArrayList<>();
    }

    public GenericResponse(boolean success) {
        this();
        this.success = success;
    }

    public GenericResponse (boolean success, List<String> messages) {
        this.success = success;
        this.message = messages;
    }

    public GenericResponse(boolean success, String... messages) {
        this(success, new ArrayList<>(Arrays.asList(messages)));
    }

    @JsonIgnore
    public boolean isFailed() {
        return !success;
    }

    public void addMessage(String message) {
        this.message.add(message);
    }

    public void setSuccessAndMessages(boolean success, @Nullable String... messages) {
        this.success = success;
        if (messages != null) {
            this.message.addAll(Arrays.asList(messages));
        }
    }

    public static GenericResponse error(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        List<String> messages = new ArrayList<>(allErrors.size());
        result.getAllErrors().forEach(e -> messages.add(e.getDefaultMessage()));
        return new GenericResponse(false, messages);
    }

    public static GenericResponse error(String... messages) {
        return new GenericResponse(false, messages);
    }

    public static GenericResponse error(List<String> messages) {
        return new GenericResponse(false, messages);
    }
}
