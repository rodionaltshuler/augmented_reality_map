package com.google.ar.core.codelabs.hellogeospatial

import com.google.ar.core.Pose
import com.google.ar.core.examples.java.common.samplerender.Mesh
import com.google.ar.core.examples.java.common.samplerender.SampleRender
import com.google.ar.core.examples.java.common.samplerender.Shader
import com.google.ar.core.examples.java.common.samplerender.VertexBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class LineRenderer(private val render: SampleRender) {
    private lateinit var lineShader: Shader
    private var lineMesh: Mesh? = null

    fun createLine(startPose: Pose, endPose: Pose) {
        // Define vertices for a line
        val vertices = floatArrayOf(
            startPose.tx(), startPose.ty(), startPose.tz(), // Start point
            endPose.tx(), endPose.ty(), endPose.tz() // End point
        )

        // Convert FloatArray to FloatBuffer
        val vertexBuffer: FloatBuffer = ByteBuffer.allocateDirect(vertices.size * java.lang.Float.BYTES)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()

        vertexBuffer.put(vertices).position(0)

        val vertexBuffers = arrayOf(VertexBuffer(render, 3, vertexBuffer))

        // Create a mesh for the line
        lineMesh = Mesh(render, Mesh.PrimitiveMode.LINES, null, vertexBuffers)

        // Initialize the shader
        lineShader = Shader.createFromAssets(render, "shaders/simple_vertex_shader.vert", "shaders/simple_fragment_shader.frag", null)
            .setVec4("u_Color", floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f)) // Red color
    }

    fun drawLine(modelViewProjectionMatrix: FloatArray) {
        lineMesh?.let { mesh ->
            lineShader.setMat4("u_ModelViewProjection", modelViewProjectionMatrix)
            render.draw(mesh, lineShader)
        }
    }
}
