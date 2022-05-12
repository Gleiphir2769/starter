package com.netease.infra.bk.broker.handle;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Bookie {
    static public void GetBookie(RoutingContext ctx, EventBus bus) {
        String productID = ctx.request().getParam("bookieID");
        bus.<JsonObject>request("MSG://EVENT/BUS",
                new JsonObject().put("message", productID),
                reply -> {
                    if (reply.succeeded()) {
                        // 发送回客户端
                        System.out.println(Thread.currentThread().getName() + ", Reply Message...");
                        System.out.println(" Message: " + reply.result().body());
                        ctx.response().end(reply.result().body().encode());
                    }
                }
        );
    }
}
