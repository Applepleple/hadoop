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
  public static Gpu newInstance(int id, String name, int totalMemoryInMb,
                                int usedMemoryInMb, int freeMemoryInMb,
                                int utilizationGpu) {
    Gpu gpu = Records.newRecord(Gpu.class);
    gpu.setId(id);
    gpu.setName(name);
    gpu.setTotalMemoryInMb(totalMemoryInMb);
    gpu.setUsedMemoryInMb(usedMemoryInMb);
    gpu.setFreeMemoryInMb(freeMemoryInMb);
    gpu.setUtilizationGpu(utilizationGpu);
    return gpu;
  }

  @Public
  @Unstable
  public abstract void setId(int id);

  @Public
  @Unstable
  public abstract int getId();

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

}
