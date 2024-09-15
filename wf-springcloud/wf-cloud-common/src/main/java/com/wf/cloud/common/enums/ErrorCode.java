package com.wf.cloud.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    BUSINESS_ERROR(500, "business error"),
    BAD_GATEWAY(502, "bad_gateway"),
    BAD_REQUEST(400, "bad_request"),
    BAD_METHOD(405, "bad_method"),
    CONFLICT(409, "conflict"),
    FORBIDDEN(403, "forbidden"),
    GATEWAY_TIMEOUT(408, "gateway_timeout"),
    HTTP_VERSION_NOT_SUPPORTED(415, "http_version_not_supported"),
    HTTP_INTERNAL_ERROR(500, "http_internal_error"),
    HTTP_BAD_GATEWAY(502, "http_bad_gateway"),
    HTTP_BAD_REQUEST(400, "http_bad_request"),
    HTTP_METHOD_NOT_ALLOWED(405, "http_method_not_allowed"),
    HTTP_NOT_ACCEPTABLE(406, "http_not_acceptable"),
    HTTP_PROXY_AUTHENTICATION_REQUIRED(407, "proxy_authentication_required"),
    INTERNAL_SERVER_ERROR(500, "internal_server_error"),
    JSON_PARSE_ERROR(400, "json_parse_erro r"),
    JSON_FORMAT_ERROR(400, "json_format_error"),
    METHOD_NOT_ALLOWED(405, "method_not_allowed"),
    NOT_FOUND(404, "not_found"),
    NOT_ACCEPTABLE(406, "not_acceptable"),
    NOT_IMPLEMENTED(503, "not_implemented"),
    OK(200, "ok"),
    PARAMETER_ERROR(400, "parameter error"),
    PAYMENT_REQUIRED(402, "payment_required"),
    PRECONDITION_FAILED(409, "precondition_failed"),
    QUOTA_EXCEEDED(404, "quota_exceeded"),
    QUOTA_TOO_HIGH(404, "quota_too_high"),
    QUOTA_TOO_LOW(404, "quota_too_low"),
    REQUEST_TIMEOUT(408, "request_timeout"),
    RESOURCE_EXHAUSTED(404, "resource_exhausted"),
    SUCCESS(200, "success"),
    TOO_MANY_REQUESTS(429, "too_many_requests"),
    UNAUTHORIZED(401, "unauthorized"),
    UNSUPPORTED_MEDIA_TYPE(415, "unsupported_media_type"),
    VERSION_NOT_SUPPORTED(415, "version_not_supported"),
    VARIANT_ALREADY_IN_PROGRESS(413, "variant_already_in_progress"),
    VARIANT_ALREADY_EXISTS(413, "variant_already_exists"),
    VARIANT_ALREADY_DELETED(413, "variant_already_deleted"),
    VARIANT_NOT_UPDATABLE(413, "variant_not_updatable"),
    VARIANT_NOT_FOUND(413, "variant_not_found"),
    XML_PARSE_ERROR(400, "xml_parse_error"),
    XML_FORMAT_ERROR(400, "xml_format_error"),
    XSL_PARSE_ERROR(400, "xsl_parse_error"),
    XSL_FORMAT_ERROR(400, "xsl_format_error"),
    YAML_PARSE_ERROR(400, "yaml_parse_error"),
    YAML_FORMAT_ERROR(400, "yaml_format_error"),
    ZIP_PARSE_ERROR(400, "zip_parse_error"),
    ZIP_FORMAT_ERROR(400, "zip_format_error"),
    ZIP_ERROR(400, "zip_error");


    private int code;
    private String msg;

}
