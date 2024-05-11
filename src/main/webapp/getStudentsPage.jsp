<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Jakarta servlets - A Java technology</title>
	<style>
		.body {
			font-family: Arial, 'sans-serif';
		}
		.studentSpan {
			font-size:16px;
			padding:8px;
		}
	</style>
</head>
<body class="body">
	<main>
		<h2>Test REST API based on JSON - GET METHOD</h2>

		<div style="width:55%;margin:3rem;padding:2rem;border-radius:1em;box-shadow: rgba(100, 100, 100, 0.3) 0px 1px 4px 0px;">
    
			<span>Realizar petici&oacute;n GET</span>
			<span>ENDPOINT getStudents</span>
			<br>

			<button type="button" id="submitBtn">solicitar datos</button>
			<br>
			<button type="button" id="showBtn">mostrar respuesta del servidor</button>
			<br>

			<span id="ctn" style="width:70%; display:grid; padding:2rem; margin-top:1rem; margin-bottom:1rem;">
				<span>...</span>
			</span>
		</div>


		<script defer>
			const submitBtn = document.getElementById("submitBtn")
			const showBtn = document.getElementById("showBtn")
			const ctn = document.getElementById("ctn")

			let fetchOptions = {
				method: "GET",
				headers: {
					"Accept": "application/json",
					"Content-Type": "application/json"
				},
			}
			let studentsArr = []

			async function handleSubmit() {
				try {
					const r = await fetch("/EliasJSONOne/getStudents", fetchOptions)
					const j = await r.json()
					console.log(j)

					studentsArr = [...j]

				} catch(fetchError) {
					console.error("Error fetching the API: ", fetchError)
				}
			}

			submitBtn.addEventListener("click", async (e) => {
				e.preventDefault()
				await handleSubmit()
			})

			function showCtn() {
				if(studentsArr.length > 0) {
					studentsArr.forEach((el, i) => {
						let span = document.createElement("span")
						span.classList.add("studentSpan")
						span.innerHTML = `Name: ${el.name}, Grade: ${el.grade}, GradeId: ${el.gradeId}`
						ctn.appendChild(span)
					})
				} else {
					alert("ha ocurrido un error al parsear la respuesta del servidor")
				}
			}

			showBtn.addEventListener("click", async (e) => {
				e.preventDefault()
				showCtn()
			})

		</script>
	</main>
</body>
</html>











