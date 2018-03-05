package org.apache.hadoop.yarn.api.records;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.util.Records;

@Public
@Unstable
public abstract class Gpu {

  @Public
  @Stable
  public static Gpu newInstance(String id, int index, String name,
                                int maxClockRate, int totalMemory) {
    Gpu gpu = Records.newRecord(Gpu.class);
    gpu.setId(id);
    gpu.setIndex(index);
    gpu.setName(name);
    gpu.setMaxClockRate(maxClockRate);
    gpu.setTotalMemory(totalMemory);
    return gpu;
  }

  @Public
  @Unstable
  public abstract void setId(String id);

  @Public
  @Unstable
  public abstract String getId();

  @Public
  @Unstable
  public abstract void setIndex(int index);

  @Public
  @Unstable
  public abstract int getIndex();

  @Public
  @Unstable
  public abstract void setName(String name);

  @Public
  @Unstable
  public abstract String getName();

  @Public
  @Unstable
  public abstract void setTotalMemory(int totalMemory);

  @Public
  @Unstable
  public abstract int getTotalMemory();

  @Public
  @Unstable
  public abstract void setMaxClockRate(int maxClockRate);

  @Public
  @Unstable
  public abstract int getMaxClockRate();

  @Override
  public boolean equals(Object gpu) {
    return gpu != null &&
        gpu instanceof Gpu &&
        getId().equals(((Gpu)gpu).getId());
  }

  @Override
  public String toString() {
    return "Gpu: index=" + getIndex() + " name=" + getName();
  }
}
