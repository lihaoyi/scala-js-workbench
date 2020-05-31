package com.lihaoyi

package object workbench {

  import upickle.default._

  /**
    * A standard way to read and write `Js.Value`s with autowire/upickle
    */
  trait ReadWrite {
    def write[Result: Writer](r: Result): ujson.Value =
      upickle.default.read[ujson.Value](upickle.default.transform(r))

    def read[Result: Reader](p: ujson. Js.Value): Result =
      upickle.default.read[Result](ujson.read(p))
  }

  /**
    * Shared API between the workbench server and the workbench client,
    * comprising methods the server can call on the client to make it do
    * things
    */
  trait WorkbenchApi {
    /**
      * Reset the HTML page to its initial state
      */
    def clear(): Unit
    /**
      * Reload the entire webpage
      */
    def reload(): Unit

    /**
      * Print a `msg` with the given logging `level`
      */
    def print(level: String, msg: String): Unit

    /**
      * Execute the javascript file available at the given `path`.
      */
    def run(path: String): Unit
  }

}
