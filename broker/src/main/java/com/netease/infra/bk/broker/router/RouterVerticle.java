package com.netease.infra.bk.broker.router;

import com.netease.infra.starter.handle.Bookie;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class RouterVerticle extends AbstractVerticle {
    @Override
    public void start() {
        final HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        EventBus bus = this.vertx.eventBus();

        router.get("/api/v1/bookies/:bookieID")
                .handler(ctx -> Bookie.GetBookie(ctx, bus));

        router.post("/api/v1/post")
                .respond(ctx -> {
                    return Future.succeededFuture(new JsonObject().put("hello", "Post"));
                });

        server.requestHandler(router).listen(8080);
    }
}
