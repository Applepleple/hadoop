/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.api.records.impl.pb;


import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.records.Gpu;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.proto.YarnProtos.GpuProto;
import org.apache.hadoop.yarn.proto.YarnProtos.ResourceProto;
import org.apache.hadoop.yarn.proto.YarnProtos.ResourceProtoOrBuilder;

import java.util.ArrayList;
import java.util.List;

@Private
@Unstable
public class ResourcePBImpl extends Resource {
  ResourceProto proto = ResourceProto.getDefaultInstance();
  ResourceProto.Builder builder = null;
  boolean viaProto = false;

  private List<Gpu> gpus = null;

  public ResourcePBImpl() {
    builder = ResourceProto.newBuilder();
  }

  public ResourcePBImpl(ResourceProto proto) {
    this.proto = proto;
    viaProto = true;
  }
  
  public ResourceProto getProto() {
    mergeLocalToProto();
    proto = viaProto ? proto : builder.build();
    viaProto = true;
    return proto;
  }

  private void mergeLocalToProto() {
    if (viaProto)
      maybeInitBuilder();
    mergeLocalToBuilder();
    proto = builder.build();
    viaProto = true;
  }

  private void mergeLocalToBuilder() {
    if (this.gpus != null) {
      addLocalGpusToProto();
    }
  }

  private void addLocalGpusToProto() {
    maybeInitBuilder();
    builder.clearGpus();
    if (this.gpus == null)
      return;
    List<GpuProto> protoList = new ArrayList<>();
    for (Gpu gpu : gpus) {
      protoList.add(convertToProtoFormat(gpu));
    }
    builder.addAllGpus(protoList);
  }

  private void maybeInitBuilder() {
    if (viaProto || builder == null) {
      builder = ResourceProto.newBuilder(proto);
    }
    viaProto = false;
  }
  
  @Override
  public int getMemory() {
    ResourceProtoOrBuilder p = viaProto ? proto : builder;
    return (p.getMemory());
  }

  @Override
  public void setMemory(int memory) {
    maybeInitBuilder();
    builder.setMemory((memory));
  }

  @Override
  public int getVirtualCores() {
    ResourceProtoOrBuilder p = viaProto ? proto : builder;
    return (p.getVirtualCores());
  }

  @Override
  public void setVirtualCores(int vCores) {
    maybeInitBuilder();
    builder.setVirtualCores((vCores));
  }

  /**
   * Get <em>the number of gpu</em> of the resource.
   * <p>
   * GPU is a new computing unit with massive computing cores. It could be used
   * to accelerate a lot of apps.
   *
   * @return <em>the number of gpu </em> of the resource
   */
  @Override
  public int getGpuNum() {
    ResourceProtoOrBuilder p = viaProto ? proto : builder;
    return (p.getGpuNum());
  }

  /**
   * Set <em>the number of gpu</em> of the resource.
   * <p>
   * GPU is a new computing unit with massive computing cores. It could be used
   * to accelerate a lot of apps.
   *
   * @param gpuNum
   */
  @Override
  public void setGpuNum(int gpuNum) {
    maybeInitBuilder();
    builder.setGpuNum((gpuNum));
  }

  /**
   * Get <em>list of gpus</em> of the resource.
   * <p>
   * GPU is a new computing unit with massive computing cores. It could be used
   * to accelerate a lot of apps.
   *
   * @return <em>the number of gpu </em> of the resource
   */
  @Override
  public List<Gpu> getGpus() {
    initGpuList();
    return gpus;
  }

  /**
   * Set <em>list of gpus</em> of the resource.
   * <p>
   * GPU is a new computing unit with massive computing cores. It could be used
   * to accelerate a lot of apps.
   *
   * @param gpus <em>the number of gpu </em> of the resource
   */
  @Override
  public void setGpus(List<Gpu> gpus) {
    if (gpus == null) {
      builder.clearGpus();
    }
    this.gpus = gpus;
  }


  @Override
  public int compareTo(Resource other) {
    int diff = this.getMemory() - other.getMemory();
    if (diff == 0) {
      diff = this.getVirtualCores() - other.getVirtualCores();
    }
    return diff;
  }

  private GpuPBImpl convertFromProtoFormat(GpuProto p) {
    return new GpuPBImpl(p);
  }

  private GpuProto convertToProtoFormat(Gpu t) {
    return ((GpuPBImpl) t).getProto();
  }

  private void initGpuList() {
    if (this.gpus != null) {
      return;
    }
    ResourceProtoOrBuilder p = viaProto ? proto : builder;
    List<GpuProto> list = p.getGpusList();
    gpus = new ArrayList<Gpu>();

    for (GpuProto gpu : list) {
      gpus.add(convertFromProtoFormat(gpu));
    }
  }

}  
