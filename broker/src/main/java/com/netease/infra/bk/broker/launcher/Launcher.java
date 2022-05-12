package com.netease.infra.bk.broker.launcher;

import io.vertx.core.Vertx;

import java.util.function.Consumer;

public interface Launcher {
    void start(Consumer<Vertx> startConsumer);
}
