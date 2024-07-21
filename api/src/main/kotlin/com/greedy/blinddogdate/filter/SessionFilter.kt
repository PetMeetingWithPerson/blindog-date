package com.greedy.blinddogdate.filter

import com.greedy.blinddogdate.logger
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class SessionFilter : WebFilter {
    val log by logger()

    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain,
    ): Mono<Void> {
//        exchange.getRequest()
//            .mutate()
//            .header(HttpHeaders.AUTHORIZATION, "Bearer " + sessionId)
//            .build();
//        return chain.filter(exchange);

        return exchange.session
            .doOnNext { session ->
                val id = session.id
                log.info("# session Id3: $id")
//                exchange.request
//                    .mutate()
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer $id")
//                    .build()
            }.then(chain.filter(exchange))
    }
}
