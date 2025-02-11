package com.github.care0717.digdag.plugin.emr_fleet

import com.github.care0717.digdag.plugin.emr_fleet.operator._
import io.digdag.client.config.Config
import io.digdag.spi._

import java.lang.reflect.Constructor
import java.util
import javax.inject.Inject

object EmrFleetPlugin {

  class EmrFleetOperatorProvider extends OperatorProvider {

    @Inject protected var systemConfig: Config = null
    @Inject protected var templateEngine: TemplateEngine = null

    override def get(): util.List[OperatorFactory] = {
      util.Arrays.asList(
        operatorFactory("emr_fleet.detect_clusters", classOf[EmrFleetDetectClustersOperator]),
        operatorFactory("emr_fleet.shutdown_cluster", classOf[EmrFleetShutdownClusterOperator]),
        operatorFactory("emr_fleet.create_cluster", classOf[EmrFleetCreateClusterOperator]),
        operatorFactory("emr_fleet.wait_cluster", classOf[EmrFleetWaitClusterOperator])
      )
    }

    private def operatorFactory[T <: AbstractEmrFleetOperator](operatorName: String, klass: Class[T]): OperatorFactory = {
      new OperatorFactory {
        override def getType: String = operatorName
        override def newOperator(context: OperatorContext): Operator = {
          val constructor: Constructor[T] = klass.getConstructor(classOf[String], classOf[OperatorContext], classOf[Config], classOf[TemplateEngine])
          constructor.newInstance(operatorName, context, systemConfig, templateEngine)
        }
      }
    }
  }
}

class EmrFleetPlugin extends Plugin {
  override def getServiceProvider[T](`type`: Class[T]): Class[_ <: T] = {
    if (`type` ne classOf[OperatorProvider]) return null
    classOf[EmrFleetPlugin.EmrFleetOperatorProvider].asSubclass(`type`)
  }
}
