package org.apache.hadoop.yarn.api.records.impl.pb;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.api.records.Gpu;
import org.apache.hadoop.yarn.proto.YarnProtos.GpuProto;
import org.apache.hadoop.yarn.proto.YarnProtos.GpuProtoOrBuilder;

@Private
@Unstable
public class GpuPBImpl extends Gpu {
  GpuProto proto = null;
  GpuProto.Builder builder = null;
  boolean viaProto = false;

  public GpuPBImpl() {
    builder = GpuProto.newBuilder();
  }

  public GpuPBImpl(GpuProto proto) {
    this.proto = proto;
    viaProto = true;
  }

  public GpuProto getProto() {
    proto = viaProto ? proto : builder.build();
    viaProto = true;
    return proto;
  }

  private void maybeInitBuilder() {
    if (viaProto || builder == null) {
      builder = GpuProto.newBuilder(proto);
    }
    viaProto = false;
  }

  @Override
  public void setId(String id) {
    maybeInitBuilder();
    builder.setId(id);
  }

  @Override
  public String getId() {
    GpuProtoOrBuilder p = viaProto ? proto : builder;
    return p.getId();
  }

  @Override
  public void setIndex(int index) {
    maybeInitBuilder();
    builder.setIndex(index);
  }

  @Override
  public int getIndex() {
    GpuProtoOrBuilder p = viaProto ? proto : builder;
    return p.getIndex();
  }

  @Override
  public void setName(String name) {
    maybeInitBuilder();
    builder.setName(name);
  }

  @Override
  public String getName() {
    GpuProtoOrBuilder p = viaProto ? proto : builder;
    return p.getName();
  }

  @Override
  public void setTotalMemory(int totalMemory) {
    maybeInitBuilder();
    builder.setTotalMemory(totalMemory);
  }

  @Override
  public int getTotalMemory() {
    GpuProtoOrBuilder p = viaProto ? proto : builder;
    return p.getTotalMemory();
  }

  @Override
  public void setMaxClockRate(int maxClockRate) {
    maybeInitBuilder();
    builder.setMaxClockRate(maxClockRate);
  }

  @Override
  public int getMaxClockRate() {
    GpuProtoOrBuilder p = viaProto ? proto : builder;
    return p.getMaxClockRate();
  }

}
