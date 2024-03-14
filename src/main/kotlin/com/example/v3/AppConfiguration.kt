package com.example.v3

import org.slf4j.MDC
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator
import org.springframework.core.task.TaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
class AppConfiguration

@Configuration
@EnableAsync
class AsyncConfiguration : AsyncConfigurer {
    override fun getAsyncExecutor(): Executor? {
        return TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
            .apply { setTaskDecorator(MdcTaskDecorator()) }
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return null
    }

    inner class MdcTaskDecorator : TaskDecorator {
        override fun decorate(runnable: Runnable): Runnable {
            val contextMap: Map<String, String>? = MDC.getCopyOfContextMap()
            return Runnable {
                try {
                    contextMap?.also { MDC.setContextMap(it) }
                    runnable.run()
                } finally {
                    MDC.clear()
                }
            }
        }
    }
}
