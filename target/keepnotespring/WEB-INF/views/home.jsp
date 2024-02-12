<%@ taglib prefix = "c" uri ="jakarta.tags.core" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>KeepNote</title>
  </head>
  <body>
    <!-- Create a form which will have text boxes for Note ID, title, content and status along with a Send 
		 button. Handle errors like empty fields -->
    <main class="container-fluid main">
      <div class="row">
        <div class="col-md-3 mt-4 text-center">
          <a href="showform" class="btn btn-dark text-white">ADD NOTE</a>
        </div>
        <c:if test="${flag}">
          <div class="col-md-6">
            <form action="addnote" method="post">
              <div class="mb-3">
                <label for="noteid" class="form-label">note id</label>
                <input
                  type="text"
                  class="form-control"
                  id="noteid"
                  name="noteId"
                  aria-describedby="emailHelp"
                />
              </div>
              <div class="mb-3">
                <label for="notetitle" class="form-label">note title</label>
                <input
                  type="text"
                  name="noteTitle"
                  class="form-control"
                  id="notetitle"
                />
              </div>
              <div class="mb-3">
                <label for="notecontent" class="form-label">note content</label>
                <input type="text" class="form-control" name="noteContent" />
              </div>
              <div class="mb-3">
                <label for="notestatus" class="form-label">note status</label>
                <input type="text" class="form-control" name="noteStatus" />
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-dark">Submit</button>
              </div>
            </form>
          </div>
        </c:if>
        <c:if test="${not empty edit}">
          <div class="col-md-6">
            <form action="updatenote/${editnote.noteId}" method="post">
              <div class="mb-3">
                <label for="noteid" class="form-label">note id</label>
                <input
                  type="number"
                  disabled
                  class="form-control"
                  id="noteid"
                  name="sgsg"
                  value="${editnote.noteId}"
                />
                <input
                  type="hidden"
                  class="form-control"
                  id="noteids"
                  name="noteId"
                  value="${editnote.noteId}"
                />
              </div>
              <div class="mb-3">
                <label for="notetitle" class="form-label">note title</label>
                <input
                  type="text"
                  name="noteTitle"
                  class="form-control"
                  id="notetitle"
                  value="${editnote.noteTitle}"
                />
              </div>
              <div class="mb-3">
                <label for="notecontent" class="form-label">note content</label>
                <input
                  type="text"
                  value="${editnote.noteContent}"
                  class="form-control"
                  name="noteContent"
                />
              </div>
              <div class="mb-3">
                <label for="notestatus" class="form-label">note status</label>
                <input
                  type="text"
                  value="${editnote.noteStatus}"
                  class="form-control"
                  name="noteStatus"
                />
              </div>
              <div class="text-center">
                <button type="submit" class="btn btn-dark">Submit</button>
              </div>
            </form>
          </div>
        </c:if>
      </div>
      <div class="row">
        <div class="col-md-6 offset-md-3">
          <table border="1" class="mt-2">
            <thead>
              <tr style="border: 1px solid black">
                <th style="border-left: 1px solid black" class="px-3 m-3">
                  NOTE ID
                </th>
                <th style="border-left: 1px solid black" class="px-3">
                  NOTE TITLE
                </th>
                <th style="border-left: 1px solid black" class="px-3">
                  NOTE CONTENT
                </th>
                <th style="border-left: 1px solid black" class="px-3">
                  NOTE STATUS
                </th>
                <th style="border-left: 1px solid black" class="px-3">
                  NOTE CREATION DATE
                </th>
                <th style="border-left: 1px solid black" class="px-3">
                  DELETE
                </th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="note" items="${notespack}">
                <tr style="border: 1px solid black">
                  <td style="border-left: 1px solid black" class="px-3">
                    ${note.noteId}
                  </td>
                  <td style="border-left: 1px solid black" class="px-3">
                    ${note.noteTitle}
                  </td>
                  <td style="border-left: 1px solid black" class="px-3">
                    ${note.noteContent}
                  </td>
                  <td style="border-left: 1px solid black" class="px-3">
                    ${note.noteStatus}
                  </td>
                  <td style="border-left: 1px solid black" class="px-3">
                    ${note.createdAt}
                  </td>

                  <td style="border-left: 1px solid black" class="px-3">
                    <a href="deletenote/${note.noteId}">
                      <i class="fa-solid fa-trash"></i>
                    </a>
                  </td>
                  <td style="border-left: 1px solid black" class="px-3">
                    <a href="updatenoteclick/${note.noteId}">
                      <i class="fa-solid fa-edit"></i>
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <c:if test="${not empty message}">
          <div class="container col-md-6"></div>
          <h1>${message}</h1>
        </c:if>
      </div>
    </main>
    <!-- display all existing notes in a tabular structure with Id, Title,Content,Status, Created Date and Action -->

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
