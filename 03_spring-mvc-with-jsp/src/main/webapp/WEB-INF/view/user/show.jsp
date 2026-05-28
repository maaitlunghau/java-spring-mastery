<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Manage User</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>

    <body>
        <div style="padding: 15px;">
            <div style="display: flex; justify-content: space-between; align-items: center;">
                <h3> Table Users</h3>
                <a class="btn btn-primary" href="/user/create">
                    Create Users
                </a>
            </div>
            <div style="margin-top: 20px;">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Address</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <th>${user.id}</th>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.address}</td>
                                <td class="d-flex" style="gap: 10px;">
                                    <a class="btn btn-warning mr-3" href="/user/${user.id}">
                                        Update
                                    </a>

                                    <form method="post" action="/user/delete/${user.id}">
                                        <button class="btn btn-danger" type="submit">
                                            Delete
                                        </button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>

    </body>

    </html>