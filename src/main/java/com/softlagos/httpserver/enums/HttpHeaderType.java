/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpHeaderType.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver.enums;

/**
 * The different HTTP Headers Fields.
 *
 * @author Rubens Gomes
 * @see "Section 5 of RFC7231 - Hypertext Transfer Protocol
 * (HTTP/1.1): Semantics and Content"
 */
public enum HttpHeaderType
{

    /**
     * The "Accept" header field can be used by user agents
     * to specify response media types that are acceptable.
     */
    ACCEPT ("Accept", "request-header"),

    /**
     * The "Accept-Charset" header field can be sent by a
     * user agent to indicate what charsets are acceptable
     * in textual response content.
     */
    ACCEPT_CHARSET ("Accept-Charset", "request-header"),

    /**
     * The "Accept-Encoding" header field can be used by user
     * agents to indicate what response content-codings are
     * acceptable in the response.
     */
    ACCEPT_ENCODING ("Accept-Encoding", "request-header"),

    /**
     * The "Accept-Language" header field can be used
     * by user agents to indicate the set of natural languages
     * that are preferred in the response.
     */
    ACCEPT_LANGUAGE ("Accept-Language", "request-header"),

    /**
     * The Accept-Ranges response-header field allows the
     * server to indicate its acceptance of range requests for
     * a resource.
     */
    ACCEPT_RANGES ("Accept-Ranges", "response-header"),

    /**
     * The Age response-header field conveys the sender's
     * estimate of the amount of time since the response
     * (or its revalidation) was generated at the origin server.
     */
    AGE ("Age", "response-header"),

    /**
     * The Allow entity-header field lists the set of
     * methods supported by the resource identified by the
     * Request-URI.
     */
    ALLOW ("Allow", "entity-header"),

    /**
     * The "Authorization" header field allows a user agent
     * to authenticate itself with an origin server --
     * usually, but not necessarily, after receiving a 401
     * (Unauthorized) response.
     */
    AUTHORIZATION ("Authorization", "request-header"),

    /**
     * The "Cache-Control" header field is used to specify
     * directives for caches along the request/response chain.
     */
    CACHE_CONTROL ("ache-Control", "general-header"),

    /**
     * The Connection general-header field allows the sender
     * to specify options that are desired for that particular
     * connection and MUST NOT be communicated by proxies over
     * further connections.
     */
    CONNECTION ("Connection", "general-header"),

    /**
     * Content coding values indicate an encoding transformation
     * that has been or can be applied to an entity. Content
     * codings are primarily used to allow a document to be
     * compressed or otherwise usefully transformed without
     * losing the identity of its underlying media type and
     * without loss of information.
     */
    CONTENT_ENCODING ("Content-Encoding", "entity-header"),

    /**
     * The Content-Language entity-header field describes
     * the natural language(s) of the intended audience for
     * the enclosed entity.
     */
    CONTENT_LANGUAGE ("Content-Language", "entity-header"),

    /**
     * The Content-Length entity-header field indicates the
     * size of the entity-body, in decimal number of OCTETs,
     * sent to the recipient or, in the case of the HEAD method,
     * the size of the entity-body that would have been sent
     * had the request been a GET.
     */
    CONTENT_LENGTH ("Content-Length", "entity-header"),

    /**
     * The Content-Location entity-header field MAY be used
     * to supply the resource location for the entity enclosed
     * in the message when that entity is accessible from a
     * location separate from the requested resource's URI.
     */
    CONTENT_LOCATION ("Content-Location", "entity-header"),

    /**
     * The Content-MD5 entity-header field, as defined in
     * RFC 1864 [23], is an MD5 digest of the entity-body for
     * the purpose of providing an end-to-end message integrity
     * check (MIC) of the entity-body.
     */
    Content_MD5 ("Content-MD5", "entity-header"),

    /**
     * The Content-Range entity-header is sent with a
     * partial entity-body to specify where in the full
     * entity-body the partial body should be applied.
     */
    CONTENT_RANGE ("Content-Range", "entity-header"),

    /**
     * The Content-Type entity-header field indicates the
     * media type of the entity-body sent to the recipient
     * or, in the case of the HEAD method, the media type
     * that would have been sent had the request been a GET.
     */
    CONTENT_TYPE ("Content-Type", "entity-header"),

    /**
     * The Date general-header field represents the date and
     * time at which the message was originated, having the same
     * semantics as orig-date in RFC 822.
     */
    DATE ("Date", "general-header"),

    /**
     * The ETag response-header field provides the current
     * value of the entity tag for the requested variant.
     */
    ETAG ("ETag", "response-header"),

    /**
     * The "Expect" header field in a request indicates
     * a certain set of behaviors (expectations) that need
     * to be supported by the server in order to properly handle
     * this request.
     */
    EXPECT ("Expect", "request-header"),

    /**
     * The Expires entity-header field gives the date/time
     * after which the response is considered stale.
     */
    EXPIRES ("Expires", "entity-header"),

    /**
     * The From request-header field, if given, SHOULD contain
     * an Internet e-mail address for the human user who
     * controls the requesting user agent.
     */
    FROM ("From", "request-header"),

    /**
     * The "Host" header field in a request provides the
     * host and port information from the target URI.
     */
    HOST ("Host", "request-header"),

    /**
     * The If-Match request-header field is used
     * with a method to make it conditional.
     */
    IF_MATCH ("If-Match", "request-header"),

    /**
     * The If-Modified-Since request-header field is used with
     * a method to make it conditional: if the requested variant
     * has not been modified since the time specified in this field,
     * an entity will not be returned from the server; instead,
     * a 304 (not modified) response will be returned without any
     * message-body.
     */
    IF_MODIFIED_SINCE ("If-Modified-Since", "request-header"),

    /**
     * The If-None-Match request-header field is used with a
     * method to make it conditional.
     */
    IF_NONE_MATCH ("If-None-Match", "request-header"),

    /**
     * If a client has a partial copy of an entity in its
     * cache, and wishes to have an up-to-date copy of the entire
     * entity in its cache, it could use the Range request-header
     * with a conditional GET (using either or both of
     * If-Unmodified-Since and If-Match.)
     */
    IF_RANGE ("If-Range", "request-header"),

    /**
     * The If-Unmodified-Since request-header field is used with
     * a method to make it conditional.
     */
    IF_UNMODIFIED_SINCE ("If-Unmodified-Since", "request-header"),

    /**
     * The Last-Modified entity-header field indicates the date
     * and time at which the origin server believes the variant
     * was last modified.
     */
    LAST_MODIFIED ("Last-Modified", "entity-header"),

    /**
     * The Location response-header field is used to redirect
     * the recipient to a location other than the Request-URI
     * for completion of the request or identification of a
     * new resource.
     */
    LOCATION ("Location", "response-header"),

    /**
     * The Max-Forwards request-header field provides a mechanism
     * with the TRACE and OPTIONS methods to limit the number of
     * proxies or gateways that can forward the request to the
     * next inbound server.
     */
    MAX_FORWARDS ("Max-Forwards", "request-header"),

    /**
     * The "Pragma" header field allows backwards compatibility
     * with HTTP/1.0 caches, so that clients can specify a
     * "no-cache" request that they will understand (as
     * Cache-Control was not defined until HTTP/1.1).
     */
    PRAGMA ("Pragma", "general-header"),

    /**
     * The Proxy-Authenticate response-header field MUST be
     * included as part of a 407 (Proxy Authentication
     * Required) response.
     */
    PROXY_AUTHENTICATE ("Proxy-Authenticate", "response-header"),

    /**
     * The Proxy-Authorization request-header field allows the
     * client to identify itself (or its user) to a proxy
     * which requires authentication.
     */
    PROXY_AUTHORIZATION ("Proxy-Authorization", "request-header"),

    /**
     * The "Range" header field on a GET request modifies
     * the method semantics to request transfer of only
     * one or more subranges of the selected representation
     * data, rather than the entire selected representation data.
     */
    RANGE ("Range", "request-header"),

    /**
     * The Referer[sic] request-header field allows the
     * client to specify, for the server's benefit, the
     * address (URI) of the resource from which the
     * Request-URI was obtained (the "referrer", although
     * the header field is misspelled.)
     */
    REFERER ("Referer", "request-header"),

    /**
     * The Retry-After response-header field can be
     * used with a 503 (Service Unavailable) response to indicate
     * how long the service is expected to be unavailable
     * to the requesting client.
     */
    RETRY_AFTER ("Retry-After", "response-header"),

    /**
     * The Server response-header field contains information
     * about the software used by the origin server to handle
     * the request.
     */
    SERVER ("Server", "response-header"),

    /**
     * The "TE" request-header header field in a request
     * indicates what transfer codings, besides chunked, the
     * client is willing to accept in response, and whether
     * or not the client is willing to accept trailer fields in a
     * chunked transfer coding.
     */
    TE ("TE", "request-header"),

    /**
     * The Trailer general field value indicates that the
     * given set of header fields is present in the trailer
     * of a message encoded with chunked transfer-coding.
     */
    TRAILER ("Trailer", "general-header"),

    /**
     * The Transfer-Encoding general-header field indicates
     * what (if any) type of transformation has been applied to
     * the message body in order to safely transfer it between the
     * sender and the recipient.
     */
    TRANSFER_ENCODING ("Transfer-Encoding", "general-header"),

    /**
     * The Upgrade general-header allows the client to specify
     * what additional communication protocols it supports and
     * would like to use if the server finds it appropriate to
     * switch protocols.
     */
    UPGRADE ("Upgrade", "general-header"),

    /**
     * The User-Agent request-header field contains
     * information about the user agent originating the request.
     */
    USER_AGENT ("User-Agent", "request-header"),

    /**
     * The Vary field value indicates the set of
     * request-header fields that fully determines,
     * while the response is fresh, whether a cache
     * is permitted to use the response to reply to a
     * subsequent request without revalidation.
     */
    VARY ("Vary", "response-header"),

    /**
     * The Via general-header field MUST be used by
     * gateways and proxies to indicate the intermediate
     * protocols and recipients between the user agent and the server on requests, and between the origin server and the client on responses.
     */
    VIA ("Via", "general-header"),

    /**
     * The Warning general-header field is used to carry
     * additional information about the status or
     * transformation of a message which might not be
     * reflected in the message.
     */
    WARNING ("Warning", "general-header"),

    /**
     * The WWW-Authenticate response-header field
     * MUST be included in 401 (Unauthorized) response
     * messages.
     */
    WWW_AUTHENTICATE ("WWW-Authenticate", "response-header");


    /**
     * Checks if this enum can be used in a Request Header.
     *
     * @return true, if this is request header
     */
    public boolean isRequestHeader()
    {
        if(v_field_name.matches(
             "general-header|request-header|entity-header"))
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if this enum can be used in a Response Header.
     *
     * @return true, if this is response header
     */
    public boolean isResponseHeader()
    {
        if(v_field_name.matches(
             "general-header|response-header|entity-header"))
        {
            return true;
        }

        return false;
    }

    /**
     * Compares the given header field with this enum constant
     * variable instance.
     *
     * @param header_field the header field name to compare
     * @return true, if successful.
     */
    public boolean headerFieldCompare(String header_field)
    {
        if(header_field == null || header_field.trim().length() == 0 )
        {
            return false;
        }

        return v_field_name.equalsIgnoreCase(header_field.trim());
    }

    /**
     * Gets the corresponding header field name for this
     * enum constant variable instance.
     *
     * @return the header field name represenstation of this
     * enum constant variable instance.
     */
    public String getFieldName()
    {
        return v_field_name;
    }

    /**
     * Gets the HTTP field type:
     * <p>
     * request-header, response-header, entity-header, general-header.
     *
     * @return the field type
     */
    public String getFieldType()
    {
        return v_field_type;
    }

    /**
     * Gets the enum constant variable instance corresponding
     * to the given HTTP header field.
     *
     * @param header_field the header field to compare
     * @return the enum constant corresponding to the given
     * header field.  If an enum constant is not found for
     * the given header field, null is returned.
     */
    public static final HttpHeaderType getEnum(String header_field)
    {
        if( header_field == null || header_field.trim().length() == 0)
        {
            return null;
        }

        if (null == TYPES)
        {
            TYPES = HttpHeaderType.class.getEnumConstants();
        }

        for (HttpHeaderType enum_type : TYPES)
        {
            if (enum_type.headerFieldCompare(header_field))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Checks if the given header field corresponds to one of the
     * enum constants defined.
     *
     * @param header_field the HTTP header field to compare
     * @return true, if the header field is valid for one of the
     * enum constants.
     */
    public static final boolean isHeaderField(String header_field)
    {
        if( header_field == null || header_field.trim().length() == 0)
        {
            return false;
        }

        return getEnum(header_field) == null ? false : true;
    }

    /**
     * Instantiates a new http header type.
     *
     * @param header_field the header_field
     * @param header_type the header_type
     */
    // ------ >>> Private <<< ------
    private HttpHeaderType(String header_field, String header_type)
    {
        v_field_name = header_field;
        v_field_type = header_type;
    }

    /** The v_field_name. */
    private final String v_field_name;

    /** The v_field_type. */
    private final String v_field_type;

    /** The types. */
    private static HttpHeaderType TYPES [] = null;

}
