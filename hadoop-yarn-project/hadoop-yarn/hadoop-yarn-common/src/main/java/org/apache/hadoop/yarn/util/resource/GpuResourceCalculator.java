package org.apache.hadoop.yarn.util.resource;

import org.apache.hadoop.yarn.api.records.Resource;

/*
 * This class calculate resource based on gpus.
 */
public class GpuResourceCalculator extends ResourceCalculator {

  @Override
  public int compare(Resource clusterResource, Resource lhs, Resource rhs) {
    return lhs.getGpuNum() - rhs.getGpuNum();
  }

  /**
   * Compute the number of containers which can be allocated given
   * <code>available</code> and <code>required</code> resources.
   *
   * @param available available resources
   * @param required  required resources
   * @return number of containers which can be allocated
   */
  @Override
  public int computeAvailableContainers(Resource available, Resource required) {
    return available.getGpuNum() / required.getGpuNum();
  }

  /**
   * Multiply resource <code>r</code> by factor <code>by</code>
   * and normalize up using step-factor <code>stepFactor</code>.
   *
   * @param r          resource to be multiplied
   * @param by         multiplier
   * @param stepFactor factor by which to normalize up
   * @return resulting normalized resource
   */
  @Override
  public Resource multiplyAndNormalizeUp(Resource r, double by, Resource stepFactor) {
    return null;
  }

  /**
   * Multiply resource <code>r</code> by factor <code>by</code>
   * and normalize down using step-factor <code>stepFactor</code>.
   *
   * @param r          resource to be multiplied
   * @param by         multiplier
   * @param stepFactor factor by which to normalize down
   * @return resulting normalized resource
   */
  @Override
  public Resource multiplyAndNormalizeDown(Resource r, double by, Resource stepFactor) {
    return null;
  }

  /**
   * Normalize resource <code>r</code> given the base
   * <code>minimumResource</code> and verify against max allowed
   * <code>maximumResource</code> using a step factor for hte normalization.
   *
   * @param r               resource
   * @param minimumResource minimum value
   * @param maximumResource the upper bound of the resource to be allocated
   * @param stepFactor      the increment for resources to be allocated
   * @return normalized resource
   */
  @Override
  public Resource normalize(Resource r, Resource minimumResource, Resource maximumResource, Resource stepFactor) {
    return null;
  }

  /**
   * Round-up resource <code>r</code> given factor <code>stepFactor</code>.
   *
   * @param r          resource
   * @param stepFactor step-factor
   * @return rounded resource
   */
  @Override
  public Resource roundUp(Resource r, Resource stepFactor) {
    return null;
  }

  /**
   * Round-down resource <code>r</code> given factor <code>stepFactor</code>.
   *
   * @param r          resource
   * @param stepFactor step-factor
   * @return rounded resource
   */
  @Override
  public Resource roundDown(Resource r, Resource stepFactor) {
    return null;
  }

  /**
   * Divide resource <code>numerator</code> by resource <code>denominator</code>
   * using specified policy (domination, average, fairness etc.); hence overall
   * <code>clusterResource</code> is provided for context.
   *
   * @param clusterResource cluster resources
   * @param numerator       numerator
   * @param denominator     denominator
   * @return <code>numerator</code>/<code>denominator</code>
   * using specific policy
   */
  @Override
  public float divide(Resource clusterResource, Resource numerator, Resource denominator) {
    return ratio(numerator, denominator);
  }

  /**
   * Determine if a resource is not suitable for use as a divisor
   * (will result in divide by 0, etc)
   *
   * @param r resource
   * @return true if divisor is invalid (should not be used), false else
   */
  @Override
  public boolean isInvalidDivisor(Resource r) {
    if (r.getGpuNum() == 0.0f) {
      return true;
    }
    return false;
  }

  /**
   * Ratio of resource <code>a</code> to resource <code>b</code>.
   *
   * @param a resource
   * @param b resource
   * @return ratio of resource <code>a</code> to resource <code>b</code>
   */
  @Override
  public float ratio(Resource a, Resource b) {
    return (float)a.getGpuNum() / b.getGpuNum();
  }

  /**
   * Divide-and-ceil <code>numerator</code> by <code>denominator</code>.
   *
   * @param numerator   numerator resource
   * @param denominator denominator
   * @return resultant resource
   */
  @Override
  public Resource divideAndCeil(Resource numerator, int denominator) {
    return null;
  }
}
