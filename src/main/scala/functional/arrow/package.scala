package functional

package object arrow {
  trait Arrow[A[-_, +_]] {
    def arrow[B, C](f: B => C): A[B, C]

    def compose[B, C, D](a1: A[B, C], a2: A[C, D]): A[B, D]

    def first[B, C, D](a: A[B, C]): A[(B, D), (C, D)]

    def second[B, C, D](a: A[B, C]): A[(D, B), (D, C)]
  }

  object Arrow {
    val Function1Arrow = new Arrow[Function1] {
      def arrow[B, C](f: B => C) = f

      def compose[B, C, D](a1: B => C, a2: C => D) = a2 compose a1

      def first[B, C, D](a: B => C) =
        (bd: (B, D)) => (a(bd._1), bd._2)

      def second[B, C, D](a: B => C) =
        (db: (D, B)) => (db._1, a(db._2))
    }
  }
}
