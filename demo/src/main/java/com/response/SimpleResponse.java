package com.response;

public class SimpleResponse extends CommonResponse<SimpleResponse.MessageCode> {


    public static SimpleResponse create(int code, String message) {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setData(new MessageCode(code, message));
        return simpleResponse;
    }


    static class MessageCode {
        private int code;
        private String message;

        MessageCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
