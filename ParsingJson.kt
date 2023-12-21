class JsonHelper(private val context: Context) {
  
  private fun parsingFileToString(fileName: String): String? {
      return try {
          // Membuka file dalam assets menggunakan nama file
          val `is` = context.assets.open(fileName)
  
          // Membaca konten file ke dalam array byte
          val buffer = ByteArray(`is`.available())
          `is`.read(buffer)
  
          // Menutup input stream setelah membaca file
          `is`.close()
  
          // Mengonversi array byte menjadi string
          String(buffer)
  
      } catch (ex: IOException) {
          // Menangani IOException jika terjadi kesalahan saat membaca file
          ex.printStackTrace()
          null
      }
  }

  //contoh penggunaan fungsi diatas
  //file json berada difolder assets
  
  fun loadCourses(): List<CourseResponse> {
          val list = ArrayList<CourseResponse>()
          try {
              val responseObject = JSONObject(parsingFileToString("CourseResponses.json").toString())
              val listArray = responseObject.getJSONArray("courses")
              for (i in 0 until listArray.length()) {
                  val course = listArray.getJSONObject(i)
  
                  val id = course.getString("id")
                  val title = course.getString("title")
                  val description = course.getString("description")
                  val date = course.getString("date")
                  val imagePath = course.getString("imagePath")
  
                  val courseResponse = CourseResponse(id, title, description, date, imagePath)
                  list.add(courseResponse)
              }
          } catch (e: JSONException) {
              e.printStackTrace()
          }
          return list
  }
}
