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
  public static Gpu newInstance(String id, int index, String name, int totalMemoryInMb,
                                int usedMemoryInMb, int freeMemoryInMb,
                                int utilizationGpu) {
    Gpu gpu = Records.newRecord(Gpu.class);
    gpu.setId(id);
    gpu.setIndex(index);
    gpu.setName(name);
    gpu.setTotalMemoryInMb(totalMemoryInMb);
    gpu.setUsedMemoryInMb(usedMemoryInMb);
    gpu.setFreeMemoryInMb(freeMemoryInMb);
    gpu.setUtilizationGpu(utilizationGpu);
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
  public abstract void setTotalMemoryInMb(int totalMemoryInMb);

  @Public
  @Unstable
  public abstract int getTotalMemoryInMb();

  @Public
  @Unstable
  public abstract void setUsedMemoryInMb(int usedMemoryInMb);

  @Public
  @Unstable
  public abstract int getUsedMemoryInMb();

  @Public
  @Unstable
  public abstract void setFreeMemoryInMb(int freeMemoryInMb);

  @Public
  @Unstable
  public abstract int getFreeMemoryInMb();

  @Public
  @Unstable
  public abstract void setUtilizationGpu(int utilizationGpu);

  @Public
  @Unstable
  public abstract int getUtilizationGpu();

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
