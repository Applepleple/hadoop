package org.apache.hadoop.yarn.util;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.util.Shell;
import org.apache.hadoop.yarn.api.records.Gpu;
import org.apache.hadoop.yarn.api.records.impl.pb.GpuPBImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is used to get gpu-related information by "nvidia-smi".
 */
@InterfaceAudience.Private
@InterfaceStability.Unstable
public class Nvml extends Shell {

  private String NVIDIA_SMI = "nvidia-smi";

  private static final String COMMA = org.apache.hadoop.util.StringUtils.COMMA_STR;

  private static final String QUERY_OPTION_ID = "index";
  private static final String QUERY_OPTION_NAME = "name";
  private static final String QUERY_OPTION_UTILIZATION_GPU = "utilization.gpu";
  private static final String QUERY_OPTION_MEMORY_TOTAL = "memory.total";
  private static final String QUERY_OPTION_MEMORY_USED = "memory.used";
  private static final String QUERY_OPTION_MEMORY_FREE = "memory.free";

  private static final String FORMAT_OPTION_CSV = "csv";
  private static final String FORMAT_OPTION_NOUNITS = "nounits";

  private List<Gpu> gpus;

  public Nvml() {
    if (Shell.WINDOWS) {
      NVIDIA_SMI = "nvidia-smi.exe";
    }
  }

  public List<Gpu> getGpuList() throws IOException {
    run();
    return gpus;
  }

  @Override
  protected String[] getExecString() {
    LOG.debug("Run command : " + NVIDIA_SMI +
        " --query-gpu=" + QUERY_OPTION_ID + COMMA + QUERY_OPTION_NAME + COMMA +
            QUERY_OPTION_UTILIZATION_GPU + COMMA + QUERY_OPTION_MEMORY_TOTAL + COMMA +
            QUERY_OPTION_MEMORY_USED + COMMA + QUERY_OPTION_MEMORY_FREE +
        " --format=" + FORMAT_OPTION_CSV + COMMA + FORMAT_OPTION_NOUNITS);
    return new String[]{ NVIDIA_SMI,
        "--query-gpu=" + QUERY_OPTION_ID + COMMA + QUERY_OPTION_NAME + COMMA +
        QUERY_OPTION_UTILIZATION_GPU + COMMA + QUERY_OPTION_MEMORY_TOTAL + COMMA +
        QUERY_OPTION_MEMORY_USED + COMMA + QUERY_OPTION_MEMORY_FREE,
        "--format=" + FORMAT_OPTION_CSV + COMMA + FORMAT_OPTION_NOUNITS };
  }

  @Override
  protected void parseExecResult(BufferedReader lines) throws IOException {
    gpus = null;

    String line = lines.readLine();
    if (line == null) {
      throw new IOException("Unable to get gpu status from " + NVIDIA_SMI);
    }
    if (line.contains("nvidia-smi: command not found")) {
      throw new IOException("nvidia-smi command not found.");
    }

    gpus = new ArrayList<>();

    if (StringUtils.isEmpty(line)) {
      return;
    }

    try {
      while (!StringUtils.isEmpty(line = lines.readLine())) {
        LOG.debug("Nvml output: " + line);
        String[] infos = line.split(COMMA);
        Gpu gpu = Gpu.newInstance(UUID.randomUUID().toString(), Integer.parseInt(infos[0].trim()),
            infos[1].trim());
        gpus.add(gpu);
      }
    } catch (IndexOutOfBoundsException | NumberFormatException e) {
      throw new IOException("Unexpected stat output: " + line, e);
    }
  }

  public static void main(String args[]) {
    try {
      List<Gpu> gpus = new Nvml().getGpuList();

      for (Gpu gpu : gpus) {
        System.out.print(gpu);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
