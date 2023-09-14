# enoca-backend

pring Boot Web Projesi

<h1>Proje Açıklaması</h1>
<p></p>Proje bir maven projesidir. Projemde veri tabanı olarak h2 database kullandım, herhangi bir veri tabanı kurmaya gerek kalmadan projeyi indirip kullanabilirsiniz.
Projede authorization ve authentication işlemleri için Spring Security kullandım. Authorization için 2 rol kullandım Admin ve User.Projenin ilk aşamasında username ve password bilgileri girilerek register işlemini gerçekleştiriyoruz.Daha sonra username ve password bilgilerimizi kullanarak login oluyoruz ve bize bir json web token yaratılıyor,bu tokeni bir yere kayıt ediyoruz, bundan sonra postmanda atacağımız tüm requestlerde bu bearer tokenı postmanda Headers alanında Authorization kısmına eklememiz gerekiyor.Yaratılan tokenın belli bi süre kullanıyoruz tokenimizin süresi dolduğunda kayıt olurken yaratılan refreh tokenı kullanarak yeni bir bearer token elde ediyoruz ve postmanda requestlerimizi atabiliyoruz.tokenın süresini application.properties dosyası içerisinde refresh.token.expires.in alanından istediğimiz şekilde değitirebiliyoruz.
Projemde yazmış olduğum endpoinler için test işlemlerini postmanda collecion oluşturarak projeme koydum ordan export edip kullanabilirsiniz.
Junit ve Mockito kütüphanesini kullanarak servis katmanında yazmış olduğum metodların gerekli unit testleri yapılmıştır inceleyebilirsiniz.
Projede setter, getter ve builderları gizlemek için lombok kütüphanesini kullandım.
</p>

<h2>Projede Kullandığım Versiyonlar </h2>
<ul>
  <li>Spring Boot 2.7.15</li>
  <li>java 17</li>
  <li>json Web Token 0.9.1</li>
  <li>mockito-junit-jupiter 5.0.0</li>
  <li>junit-jupiter-api 5.9.0</li>
</ul>
