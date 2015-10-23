/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.sparkta.plugin.operator.accumulator

import java.io.{Serializable => JSerializable}
import scala.util.Try

import com.stratio.sparkta.sdk.TypeOp._
import com.stratio.sparkta.sdk.{TypeOp, _}

class AccumulatorOperator(name: String, properties: Map[String, JSerializable]) extends Operator(name, properties)
with ProcessMapAsAny {

  override val defaultTypeOperation = TypeOp.ArrayString

  override val writeOperation = WriteOp.AccSet

  override def processReduce(values: Iterable[Option[Any]]): Option[Any] =
    Try(Some(transformValueByTypeOp(returnType, getDistinctValues(values.flatten.map(_.toString)))))
      .getOrElse(Some(""))
}