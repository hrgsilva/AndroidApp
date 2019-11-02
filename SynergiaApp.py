from flask import Flask, jsonify, request
import json
import urllib.request
import random

app = Flask(__name__)

vagas = [{"id": e, "nome": "Vaga "+str(e), "ementa":"Ementa "+str(e), "foto":"https://i.pinimg.com/originals/61/8a/8b/618/a8b692c3c12c08a20e2f7fd3c773f.jpg", "professor": "Professor Vaga "+str(e)} for e in range(1,11)]   

@app.route("/vagas", methods=['GET'])
def get():
    return jsonify(vagas)

@app.route("/vagas/<int:id>", methods=['GET'])
def get_one(id):
    filtro = [e for e in vagas if e["id"] == id]
    if filtro:
        return jsonify(filtro[0])
    else:
        return jsonify({})

@app.route("/vagas", methods=['POST'])
def post():
    global vagas
    try:
        content = request.get_json()

        # gerar id
        ids = [e["id"] for e in vagas]
        if ids:
            nid = max(ids) + 1
        else:
            nid = 1
        content["id"] = nid
        vagas.append(content)
        return jsonify({"status":"OK", "msg":"Vaga adicionada com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/vagas/<int:id>", methods=['DELETE'])
def delete(id):
    global vagas
    try:
        vagas = [e for e in vagas if e["id"] != id]
        return jsonify({"status":"OK", "msg":"Vaga removida com sucesso"})
    except Exception as ex:
        return jsonify({"status":"ERRO", "msg":str(ex)})

@app.route("/push/<string:key>/<string:token>", methods=['GET'])
def push(key, token):
	d = random.choice(vagas)
	data = {
		"to": token,
		"notification" : {
			"title":d["nome"],
			"body":"Você tem nova atividade em "+d['nome']
		},
		"data" : {
			"VagaId":d['id']
		}
	}
	req = urllib.request.Request('http://fcm.googleapis.com/fcm/send')
	req.add_header('Content-Type', 'application/json')
	req.add_header('Authorization', 'key='+key)
	jsondata = json.dumps(data)
	jsondataasbytes = jsondata.encode('utf-8')   # needs to be bytes
	req.add_header('Content-Length', len(jsondataasbytes))
	response = urllib.request.urlopen(req, jsondataasbytes)
	print(response)
	return jsonify({"status":"OK", "msg":"Push enviado"})


if __name__ == "__main__":
    app.run(host='localhost')
