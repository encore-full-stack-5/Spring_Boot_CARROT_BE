package com.example.carrot_market.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class CommonError extends RuntimeException {
    protected String message;
    protected HttpStatus httpStatus;

    protected CommonError(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

     public ResponseEntity<BaseResponseEntity<?>> convertResponse()  {
        return BaseResponseEntity.fail(this.httpStatus, this.message);
    }


    public static abstract class Expected extends CommonError {
        protected Expected(String message, HttpStatus httpStatus) {
            super(message, httpStatus);
        }

        public static class MethodArgumentNotValidException extends Expected {
            public MethodArgumentNotValidException() {
                super("요청한 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
        }

        public static class MissingServletRequestParameterException extends Expected {
            public MissingServletRequestParameterException() {
                super("필수 요청 매개변수가 누락되었습니다.", HttpStatus.BAD_REQUEST);
            }
        }

        public static class BusinessException extends Expected {
            public BusinessException() {
                super("비즈니스 규칙 위반입니다.", HttpStatus.BAD_REQUEST);
            }
        }

        public static class AuthenticationException extends Expected {
            public AuthenticationException() {
                super("유효하지 않은 인증 요청입니다.", HttpStatus.UNAUTHORIZED);
            }
        }

        public static class ResourceNotFoundException extends Expected {
            public ResourceNotFoundException(String message) {
                super(message, HttpStatus.NOT_FOUND);
            }

            public ResourceNotFoundException() {
                super("찾는 데이터가 없습니다.", HttpStatus.NOT_FOUND);
            }

        }
    }

    public static abstract class Unexpected extends CommonError {
        protected Unexpected(String message, HttpStatus httpStatus) {
            super(message, httpStatus);
        }

        public static class AuthenticationException extends Unexpected {
            public AuthenticationException() {
                super("허용되지 않은 인증 접근입니다.", HttpStatus.UNAUTHORIZED);
            }
        }

        public static class AccessDeniedException extends Unexpected {
            public AccessDeniedException() {
                super("접근 거부되었습니다.", HttpStatus.FORBIDDEN);
            }
        }

        public static class DataIntegrityViolationException extends Unexpected {
            public DataIntegrityViolationException() {
                super("데이터 무결성 위반입니다.", HttpStatus.CONFLICT);
            }
        }

        public static class NullPointerException extends Unexpected {
            public NullPointerException() {
                super("널 참조 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public static class IllegalArgumentException extends Unexpected {
            public IllegalArgumentException() {
                super("부적절한 인자가 전달되었습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public static class SystemException extends Unexpected {
            public SystemException() {
                super("시스템 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        public static class TimeoutException extends Unexpected {
            public TimeoutException() {
                super("요청 시간이 초과되었습니다.", HttpStatus.REQUEST_TIMEOUT);
            }
        }
    }
}
