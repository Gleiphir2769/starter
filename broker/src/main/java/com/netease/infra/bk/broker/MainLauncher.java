package com.netease.infra.bk.broker;

import com.netease.infra.bk.broker.bookie.WorkerVerticle;
import com.netease.infra.bk.broker.launcher.ClusterLauncher;
import com.netease.infra.bk.broker.launcher.Launcher;
import com.netease.infra.bk.broker.launcher.SingleLauncher;
import com.netease.infra.bk.broker.router.RouterVerticle;
import io.vertx.core.DeploymentOptions;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MainLauncher {

    private static final ConcurrentMap<String, String> IDS = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        final boolean isClustered = false;
        final Launcher launcher = isClustered ? new ClusterLauncher() :
                new SingleLauncher();
        // 设置Options
        launcher.start(vertx -> {
            // 发布Standard
            vertx.deployVerticle(RouterVerticle::new,
                    new DeploymentOptions().setInstances(10));
            // 发布Worker
            vertx.deployVerticle(WorkerVerticle::new,
                    new DeploymentOptions().setWorker(true).setInstances(10));
        });
    }
}

