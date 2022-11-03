package org.example;

/**
 * Class main.
 * GRASP - Responsible for starting user interface
 */
class ProgramMain {
  /**
   * GRASP - Creator.
   * instances of Main have the initializing
   * information for instances of UserInterface
   *
   * @param args arguments of main
   */
  public static void main(final String[] args) {
    new UserInterface();
  }
}