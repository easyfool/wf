/**
 * 全链路日志
 * @author wangfeng
 * @mail wangfengbabe@163.com
 * @data 2024/9/15 21:42
 * @version 1.0
 *
 * 有多种实现方式
 *
 * 方式一 对controller加切面处理
 *
 * 可以通过 HttpServletRequest 中的 InputStream 或 reader 来获取请求的数据，但如果我们直接在这里读取了流或内容，到后面的逻辑将无法进行下去，所以需要实现一个可以缓存的 HttpServletRequest。好在 Spring 提供这样的类，就是 ContentCachingRequestWrapper 和 ContentCachingResponseWrapper， 根据官方的文档这两个类正好是来干这个事情的，我们只要将 HttpServletRequest 和 HttpServletResponse 转化即可。
 *
 * HttpServletRequest wrapper that caches all content read from the input stream and reader, and allows this content to be retrieved via a byte array.
 *
 * Used e.g. by AbstractRequestLoggingFilter. Note: As of Spring Framework 5.0, this wrapper is built on the Servlet 3.1 API.
 *
 * HttpServletResponse wrapper that caches all content written to the output stream and writer, and allows this content to be retrieved via a byte array.
 *
 * Used e.g. by ShallowEtagHeaderFilter. Note: As of Spring Framework 5.0, this wrapper is built on the Servlet 3.1 API.
 */
package com.wf.trace;