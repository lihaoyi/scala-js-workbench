package example
import scala.scalajs.js.annotation.{JSExportTopLevel, JSExport}
import org.scalajs.dom.{html, document, window, CanvasRenderingContext2D}
import scala.util.Random

case class Point(x: Int = Random.nextInt(255), 
                 y: Int = Random.nextInt(255), 
                 dx: Int = Random.nextInt(3) - 1, 
                 dy: Int = Random.nextInt(3) - 1){
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

@JSExportTopLevel("example.ScalaJSExample")
object ScalaJSExample {
  val ctx =
    document
       .getElementById("canvas")
       .asInstanceOf[html.Canvas]
       .getContext("2d")
       .asInstanceOf[CanvasRenderingContext2D]

  var p = Point(128, 128)
  var color = "black"

  var enemiess = List.fill(10)(Point())
  def clear() = {
    ctx.fillStyle = color
    ctx.fillRect(0, 0, 255, 255)
  }

  def run(time: Double): Unit = {
    clear()
    ctx.fillStyle = "yellow"
    p = Point(p.x, (p.y + 2) % 255)
    ctx.fillRect(p.x, p.y, 5, 20)
    enemiess = for (enemy <- enemiess) yield {
      ctx.fillStyle = "red"
      ctx.fillRect(enemy.x, enemy.y, 10, 10)
      enemy.copy(x = (enemy.x + enemy.dx + 255) % 255, y = (enemy.y + enemy.dy + 255) % 255)
    }
    window.requestAnimationFrame(this.run)
  }

  @JSExport
  def main(): Unit = {
    run(0)
  }
}
